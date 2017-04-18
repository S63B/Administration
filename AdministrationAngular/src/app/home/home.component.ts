import { Component, OnInit } from '@angular/core';
import { Ride } from "app/ride";
import { RideService } from "app/ride.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  rides: Ride[];

  constructor(private rideService: RideService) { }

  ngOnInit() {
    let date = new Date();
    let currentMilis: number = date.getMilliseconds();
    date.setMonth(date.getMonth() - 1);
    let prevMonthMilis: number = date.getMilliseconds();

    this.getRidesBetween(prevMonthMilis, currentMilis);
  }

  public getRidesBetween(startTime: number, endTime: number) {
    this.rideService.getRidesBetween('bramdb', startTime, endTime).subscribe(rides => {
      this.rides = rides;
    });
  }
}
