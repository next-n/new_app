import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { UsersService } from './users.service';
import { User } from './user.model';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
// import { MatTableDataSource } from '@angular/material/table';
import { UserDatasource } from './user.datasource';
import {
  MatDialog,
  MAT_DIALOG_DATA,
  MatDialogRef,
} from '@angular/material/dialog';
import { SaveuserComponent } from '../saveuser/saveuser.component';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
})
export class UsersComponent {
  displayedColumns: string[] = ['username', 'update', 'delete'];

  length!: number;
  pageSize = 5;
  pageIndex = 0;
  pageSizeOptions = [5, 10, 25];
  pageEvent!: PageEvent;
  test!: String;
  users: User[] = [];
  dataSource!: UserDatasource;
  //   dataSource = this.users;
  constructor(
    private http: HttpClient,
    private userService: UsersService,
    private dialog: MatDialog
  ) {}
  //   @ViewChild(MatPaginator)
  //   paginator!: MatPaginator;

  //   ngAfterViewInit() {
  //     this.dataSource.paginator = this.paginator;
  //   }
  handlePageEvent(e: PageEvent) {
    this.pageEvent = e;
    this.pageSize = e.pageSize;
    this.pageIndex = e.pageIndex;
    this.dataSource.loadUsers(this.pageIndex, this.pageSize);
  }
  ngOnInit(): void {
    this.userService.getTotalUsers().subscribe((res) => (this.length = res));
    this.dataSource = new UserDatasource(this.userService);
    this.dataSource.loadUsers();
  }
  addData() {
    const dialogRef = this.dialog.open(SaveuserComponent, {
      data: { username: '', password: '' },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != null) {
        this.userService.postUser(result).subscribe((result) => {
          this.dataSource.loadUsers(this.pageIndex, this.pageSize);
          this.userService.getTotalUsers().subscribe((res) => {
            console.log(res);
            this.length = res;
          });
        });
      }
    });
  }
  Delete(username: string) {
    //  console.log(username);
    this.userService.deleteUser(username).subscribe((result) => {
      console.log(result);
      this.dataSource.loadUsers(this.pageIndex, this.pageSize);
      this.userService.getTotalUsers().subscribe((res) => {
        console.log(res);
        this.length = res;
      });
    });
  }
  Update(id: number, username: string) {
    const dialogRefupdate = this.dialog.open(SaveuserComponent, {
      data: { username: username, password: '' },
    });
    dialogRefupdate.afterClosed().subscribe((result) => {
      if (result != null) {
        this.userService.updateUser(id, result).subscribe((result) => {
          //  console.log(result);
          this.dataSource.loadUsers(this.pageIndex, this.pageSize);
          this.userService.getTotalUsers().subscribe((res) => {
            console.log(res);
            this.length = res;
          });
        });
      }
    });
  }
}
