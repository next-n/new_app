import { CollectionViewer, DataSource } from '@angular/cdk/collections';
import { BehaviorSubject, catchError, finalize, Observable, of } from 'rxjs';
import { User } from './user.model';
import { UsersService } from './users.service';

export class UserDatasource implements DataSource<User> {
  private usersSubject = new BehaviorSubject<User[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  constructor(private usersService: UsersService) {}

  connect(collectionViewer: CollectionViewer): Observable<readonly User[]> {
    return this.usersSubject.asObservable();
  }
  disconnect(collectionViewer: CollectionViewer): void {
    this.usersSubject.complete();
    this.loadingSubject.complete();
  }
  loadUsers(pageIndex = 0, pageSize = 5) {
    this.loadingSubject.next(true);

    this.usersService
      .getUser(pageIndex, pageSize)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe((users) => this.usersSubject.next(users));
  }
}
