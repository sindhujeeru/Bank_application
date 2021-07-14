import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { DashboardService } from 'src/app/services/dashboard/dashboard.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  user: User;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formService: DashboardService
  ) {
    this.user = new User();
  }

  onSubmit() {
    this.formService
      .saveCustomer(this.user)
      .subscribe((result) => this.gotoLogin());
  }

  gotoLogin() {
    console.log('Account created');
    this.router.navigate(['/login']);
  }
}
