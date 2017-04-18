<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
import { RideService } from "app/ride.service";
=======
import { Component, OnInit, Input } from '@angular/core';
>>>>>>> refs/remotes/origin/Single-invoice-screen
import { Ride } from "app/ride";

@Component({
  selector: 'app-rides',
  templateUrl: './rides.component.html',
  styleUrls: ['./rides.component.css']
})
export class RidesComponent implements OnInit {
  @Input() rides: Ride[];

  constructor() { }

  ngOnInit() {
  }

}
