import { Component, OnInit, ViewChild } from '@angular/core';
import { UserModel } from './user.model';
import { UserService } from './user.service';
import { UploadComponent } from '../upload/upload.component';
import { DialogService } from 'primeng/dynamicdialog';
import { LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})

export class UserComponent implements OnInit {

  users: UserModel[] = [];
  cols: any[] = [];
  loading = false;
  minMax: number[] = [0.0, 4000];
  totalRecords: number = 0;
  @ViewChild('tb') table: Table | undefined;

  constructor(private userService: UserService, public dialogService: DialogService) { }

  ngOnInit() {
    this.loading = true;
    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'salary', header: 'Salary' }
    ];
    this.userService.getUsers().then((data: any) => {
      this.totalRecords = data.results.length;
    });
  }


  loadData(event: LazyLoadEvent) {
    let min: any;
    let max: any;
    let offset = event.first;
    let limit = this.totalRecords;
    if (event.filters && event.filters['salary']) {
      const salary: any = Object.values(event.filters['salary'])[0].value;
      if (salary) {
        min = salary[0];
        max = salary[1];
      }
    }

    const sort = event.sortField;
    setTimeout(() => {
      this.users = [];
      this.userService.getUsersWithParams(min, max, limit, offset, sort).then((data: any) => {
        const userList = data.results;

        userList.forEach((user: UserModel) => {
          this.users.push(user);
        });
      });
      this.loading = false;
    }, 1000);
  }




  onUploadFile() {
    const ref = this.dialogService.open(UploadComponent, {
      header: 'Upload',
      width: '70%'
    });

    ref.onClose.subscribe((data) => {
      this.table?.reset();
      this.table?.clearState();
  });
  }

}
