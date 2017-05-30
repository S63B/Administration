import { Component, OnInit } from '@angular/core';
import { CarService } from "app/car.service";

@Component({
  selector: 'app-carowner',
  templateUrl: './carowner.component.html',
  styleUrls: ['./carowner.component.css']
})
export class CarownerComponent implements OnInit {

  private cars = [];
  private owners = [];

  constructor(private carService: CarService) { }

  ngOnInit() {
    this.getOwnersCars(1);
    this.getOwners();
  }

  onChange(val) {
    this.getOwnersCars(val);
  }

  private getOwners() {
    this.carService.getOwners().subscribe(owners => {
      this.owners = owners;
      //this.owners.push({id: 2, name: 'Henk'})
    });
  }

  private getOwnersCars(ownerId: number) {
    this.carService.getOwnersCars(ownerId).subscribe(cars => {
      this.cars = cars;
    });
  }

}
