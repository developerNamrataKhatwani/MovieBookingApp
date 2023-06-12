import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  isLoggedIn = false;
  primary = "primary"
  accent = "accent"
  fullname: any = '';
  constructor(public loginService: LoginService, private router: Router) {
    this.isLoggedIn = this.loginService.isLoggedIn();
    console.log(this.isLoggedIn);
    this.loginService.loginStatusSubject
      .asObservable()
      .subscribe((data: any) => {
        this.isLoggedIn = this.loginService.isLoggedIn();
        
        if (this.isLoggedIn) {
          console.log('works')
          this.loginService.getCurrentUser().subscribe(
            (user:any)=>{
              console.log(user);
              
              this.fullname= user.fullName
            }
          );
          console.log(this.fullname)
        }
      });
  }

  ngOnInit() {

  }

  logout() {
    this.loginService.logOut();
  }

}
