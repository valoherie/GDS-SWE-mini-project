import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  public getUsers() {
    const url = 'http://localhost:8080/users';
    const promise = new Promise((resolve, reject) => {
      this.httpClient.get(url).toPromise().then(
        res => {
          resolve(res)
        }, err => {
          reject(err);
        }
      )
    });
    return promise;
  }

  public getUsersWithParams(min:any, max:any, limit:any, offset:any, sort:any) {
    let url = 'http://localhost:8080/users?';
    if (min) {
      url = `${url}min=${min}&`;
    }
    if (max) {
      url = `${url}max=${max}&`;
    }
    if (offset) {
      url = `${url}offset=${offset}&`;
    }
    if (limit) {
      url = `${url}limit=${limit}&`;
    }
    if (sort) {
      url = `${url}sort=${sort}`;
    }
    const promise = new Promise((resolve, reject) => {
      this.httpClient.get(url).toPromise().then(
        res => {
          resolve(res)
        }, err => {
          reject(err);
        }
      )
    });
    return promise;
  }
}
