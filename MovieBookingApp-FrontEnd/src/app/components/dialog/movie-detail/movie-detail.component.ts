import { Component, Inject } from '@angular/core';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { MovieService } from 'src/app/service/movie.service';
import Swal from 'sweetalert2';
import { AddMovieComponent } from '../../addMovie/add-movie/add-movie.component';
import { LoginComponent } from '../../login/login/login.component';
import { BookComponent } from '../book/book.component';
import { ForgotComponent } from '../forgot/forgot.component';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css'],
})
export class MovieDetailComponent {
  movie = {
    movieName: '',
    rating: 0,
    genre: '',
    audio: '',
    seatsAvailable: '',
    theatreName: '',
    imgUrl : '',
  };

  roleStatus = false;

  constructor(
    private dialogRef: MatDialogRef<ForgotComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { movieName: any },
    private movieService: MovieService,
    private dialog: MatDialog,
    private loginService: LoginService,
    private router: Router
  ) {
    if (this.loginService.getRole() == 'ROLE_ADMIN') {
      this.roleStatus = true;
    }

    this.movieService.getMovie(data.movieName).subscribe(
      (movie: any) => {
        this.movie = movie;
        console.log(movie);
      },
      (error) => {
        this.loginService.logOut();
        Swal.fire('error', 'Session expired!', 'error');
        this.router.navigate(['']);
      }
    );
  }

  deleteMovie() {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, I am sure.',
    }).then((result) => {
      if (result.isConfirmed) {
        this.movieService.deleteMovie(this.movie.movieName).subscribe(
          (data: any) => {
            if (data == 'Not Authorized') {
              this.loginService.logOut();
              Swal.fire('error', 'Session expired!', 'error');
              this.router.navigate(['']);
            }
            Swal.fire('Deleted!', data, 'success');
          },
          (error) => {
            Swal.fire('error', 'Unexpected error!', 'error');
          }
        );
      }
    });

    this.closeDialog();
  }

  openDialog() {
    this.dialog.open(BookComponent, {
      data: {
        movieName: this.movie.movieName,
        seats: this.movie.seatsAvailable,
      },
    });
    this.closeDialog();
  }

  openDialogUpdate(movie: any) {
    this.dialog.open(AddMovieComponent, {
      width: '90%',
      height: '95%',
      data: { movie: movie, flag:false },
    });
  }

  closeDialog() {
    this.dialogRef.close();
  }
}
