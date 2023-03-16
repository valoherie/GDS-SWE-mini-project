import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { UploadComponent } from './upload/upload.component';
import { UserService } from './user/user.service';
import { HttpClientModule } from '@angular/common/http';

//PrimeNg
import {TableModule} from 'primeng/table';
import {CardModule} from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import {FileUploadModule} from 'primeng/fileupload';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {DialogService, DynamicDialogModule} from 'primeng/dynamicdialog';
import {SliderModule} from 'primeng/slider';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UploadComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    TableModule,
    CardModule,
    ButtonModule,
    FileUploadModule,
    MessageModule,
    MessagesModule,
    DynamicDialogModule,
    SliderModule
  ],
  providers: [
    UserService,
    DialogService
  ],
  entryComponents: [UploadComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
