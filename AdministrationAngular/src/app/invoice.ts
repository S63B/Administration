import { User } from "app/user";

export class Invoice {
    constructor(
        public id: number,
        public user: User,
        public date: Date,
        public totalPrice: number,
        public startDate: Date,
        public endDate: Date,
        public paymentStatus: number,
        public countryOfOrigin: string
    ) {

    }
}
