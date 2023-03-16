import { Component } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent {


  showErrorMessage = false;
  errorMessage = '';
  showSuccessMessage = false;
  successMessage = '';
  uploadRequest = 'http://localhost:8080/upload';


  onUploadFile(event: any) {
    const response: Object = event.originalEvent.body;
    if (response) {
      this.showErrorMessage = false;
      this.showSuccessMessage = true;
      this.successMessage = 'File Uploaded Successfully.'
    }
  }

  onError(event:any) {
    this.showSuccessMessage = false;
    this.showErrorMessage = true;
    if (event.error.error) {
      this.errorMessage = event.error.error;
    } else {
      this.errorMessage = 'Failed to upload file. Please try again.';
    }
  }
}
