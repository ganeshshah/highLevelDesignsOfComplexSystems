# Design BookMyShow type of booking management app

## Requirement Gathering

- Login and signup users
- App has to support multiple cities
- Cities can have multiple theatre
- Every theatre have multiple auditorium
- There can be multiple movies being played across the system at the same time
- Seats category / type
- We need to support adding movies
- Assume we own all the theatre
- One seat should not be booked by more than one person for a show
- Upper bound for max seat booked per person
- Payment can be made via CC / DD / UPI
- Support cancellation of a ticket
- City ==> Theatre ==> show1, show2, show 3 ( Time, Date, Audi )
- People should be shown available seats and allowed to select seat when they are making a booking. 
- No dynamic pricing
- Different price for different type of seats.
- If payment not done after booking within 10 minutes, booking cancelled
- Allow booking before 30 minutes to 10 days

