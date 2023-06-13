import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { MovieService } from 'src/app/service/movie.service';
import Swal from 'sweetalert2';
import { AddMovieComponent } from '../../addMovie/add-movie/add-movie.component';
import { ForgotComponent } from '../../dialog/forgot/forgot.component';
import { MovieDetailComponent } from '../../dialog/movie-detail/movie-detail.component';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css'],
})
export class MovieComponent implements OnInit {

  movie: any;

  roleStatus = false;

  constructor(
    // private dialogRef: MatDialogRef<ForgotComponent>,
    // @Inject(MAT_DIALOG_DATA) public data: { movieName: any },
    private movieService: MovieService, private dialog: MatDialog, private loginService: LoginService, private router: Router) {


  }

  ngOnInit(): void {

    this.movieService.getAllMovie().subscribe(
      (movie: any) => {
        this.movie = movie;
        if (this.loginService.getRole() == "ROLE_ADMIN") {
          this.roleStatus = true;
        }
      },
      (error: any) => {
        Swal.fire("error", "Session expired!", "error");
        this.loginService.logOut();
        this.router.navigate([""]);
      }
    );


  }

  addMovie() {
    this.dialog.open(AddMovieComponent, {
      width: '90%',
      height: '95%',
      data: { movie: '', flag: true},
    });

     this.router.navigate(['movie']);
  }

  openDialog(movieName: String) {
    this.dialog.open(MovieDetailComponent, {
      data: { movieName: movieName },
    });
  }
}
