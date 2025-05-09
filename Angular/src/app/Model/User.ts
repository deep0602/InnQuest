export class User{
    constructor(firstName: string, lastName: string, password: string, email: string, phoneNo: string, roles: string[]){
        this.id=null
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNo;
        this.roles = roles;
    }
    id: number|null
    firstName: string
    lastName:string
    password: string
    email: string
    phoneNumber: string
    roles: string[]
}