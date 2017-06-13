import { Component, OnInit } from '@angular/core';
import { CarService } from "app/car.service";
import { Validators, FormBuilder } from "@angular/forms";

@Component({
  selector: 'app-carowner',
  templateUrl: './carowner.component.html',
  styleUrls: ['./carowner.component.css']
})
export class CarownerComponent implements OnInit {

  private cars = [];
  private owners = [];
  private owner;
  private ownership;
  private invoices = [];

  public addCarForm = this.fb.group({
    carID: ["", Validators.required]
  });

  constructor(private carService: CarService, public fb: FormBuilder) { }

  addCar(event) {
    this.addCarToOwner(this.owner.id, this.addCarForm.controls.carID.value);
  }

  ngOnInit() {
    this.getOwner(1);
    this.getOwnersCars(1);
    this.getOwners();
    this.getOwnerInvoices(1);
  }

  onChange(val) {
    this.getOwnersCars(val);
    this.getOwner(val);
    this.getOwnerInvoices(val);
  }

  private getOwners() {
    this.carService.getOwners().subscribe(owners => {
      this.owners = owners;
    });
  }

  private getOwner(ownerId: number) {
    this.carService.getOwner(ownerId).subscribe(owner => {
      this.owner = owner;
    });
  }

  private getOwnersCars(ownerId: number) {
    this.carService.getOwnersCars(ownerId).subscribe(cars => {
      this.cars = cars;
    });
  }

  private getOwnerInvoices(ownerId: number) {
    this.carService.getOwnerInvoices(ownerId).subscribe(invoices => {
      this.invoices = invoices;
    });
  }

  private addCarToOwner(ownerId: number, carId: number){
    this.carService.addCarToOwner(ownerId, carId).subscribe(ownership => {
      this.ownership = ownership;
      this.getOwnersCars(ownerId);
    });
  }
}
