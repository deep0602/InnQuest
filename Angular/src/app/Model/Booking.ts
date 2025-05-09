export class Booking {
    bookingId!: number;
    userId!: number|null;
    accommodationId!: number |null;
    bookingDate!: Date;
    checkInDate!: Date;
    checkOutDate!: Date;
    totalPeople!: number;
    totalPrice!: number;
    status!: string;
    pricePerDay!: number;
    accommodationDetails!: string
  }
