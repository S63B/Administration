import { Component, OnInit, Input } from '@angular/core';
import { Invoice } from "app/invoice";
import { Ride } from "app/ride";
import { User } from "app/user";

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  @Input() invoice?: Invoice;
  private invoiceRides: Ride[];

  constructor() {
    this.invoice = new Invoice(1, new User(), new Date(), 500.00, new Date('03-01-2017'), new Date('03-31-2017'), 10, 'Nederland');
   }

  ngOnInit() {
  }

}
