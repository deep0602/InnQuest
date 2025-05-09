export class AccommodationWithImage {
    accommodationId: number | null;
    accommodationName: string;
    description: string;
    location: string;
    checkInDate: Date;
    checkOutDate: Date;
    amenities: string;
    pricePerNight: number;
    distance: number;
    rating: number;
    userId: number | null ;
    imagePath: string | null;

    constructor(
        accommodationName: string,
        description: string,
        location: string,
        checkInDate: Date,
        checkOutDate: Date,
        amenities: string,
        pricePerNight: number,
        distance: number,
        rating: number,
        userId: number | null,
        imagePath: string | null
    ) {
        this.accommodationId = null;
        this.accommodationName = accommodationName;
        this.description = description;
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.amenities = amenities;
        this.pricePerNight = pricePerNight;
        this.distance = distance;
        this.rating = rating;
        this.userId = userId;
        this.imagePath = imagePath;
    }
}
