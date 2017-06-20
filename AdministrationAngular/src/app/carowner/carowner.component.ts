import { Component, OnInit } from '@angular/core';
import { CarService } from "app/car.service";
import { Validators, FormBuilder } from "@angular/forms";

@Component({
  selector: 'app-carowner',
  templateUrl: './carowner.component.html',
  styleUrls: ['./carowner.component.css']
})
export class CarownerComponent implements OnInit {

  private _cars = [];
  private _owners = [];
  private _owner;
  private _ownership;
  private _invoices = [];

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

      for (var i = this.owners.length - 1; i >= 0; i--) {
        if (this.owners[i]["name"] == null) {
          this.owners.splice(i, 1);
        }
      }
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

  public get cars() {
    return this._cars;
  }

  public get owners() {
    return this._owners;
  }

  public get owner() {
    return this._owner;
  }

  public get ownership() {
    return this._ownership;
  }

  public get invoices() {
    return this._invoices;
  }

  public set cars(cars:any[]){
    this._cars = cars;
  }

  public set owners(owners:any[]){
    this._owners = owners;
  }

  public set owner(owner:any){
    this._owner = owner;
  }

  public set ownership(ownership:Object){
    this._ownership = ownership;
  }

  public set invoices(invoices:Object[]){
    this._invoices = invoices;
  }
}
