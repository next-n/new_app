import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from '../users/user.model';

@Component({
  selector: 'app-saveuser',
  templateUrl: './saveuser.component.html',
  styleUrls: ['./saveuser.component.css'],
})
export class SaveuserComponent implements OnInit {
  title!: string;
  ok!: string;
  constructor(
    public dialogRef: MatDialogRef<SaveuserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {}
  ngOnInit(): void {
    if (this.data.username.trim() != '') {
      this.title = 'Update User';
      this.ok = 'Update';
    } else {
      this.title = 'Add User';
      this.ok = 'Save';
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  handleLogin(): void {}
}
