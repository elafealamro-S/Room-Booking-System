# Room Booking & Event Management System

A Java console application that models an automated venue reservation workflow using polymorphic array tracking, validation layers, and dynamic input parsing.

## 🚀 How it Works
The application parses live enterprise commands from an input script to handle real-time scheduling constraints:
* **Polymorphic Room Allocations:** Registers venue types (`ClassRoom` with projector fields or `Lab` with OS specifications) into a unified abstract `Room` collection.
* **Conditional Booking Validation:** The booking engine (`BOOKING`) verifies constraints before confirming a reservation:
  * Ensures the employee ID and event name exist in the registry.
  * Rejects operations if the targeted venue status is already set to `Reserved`.
  * Validates that the venue's physical `Capacity` can accommodate the event's `AttendeesNo`.
* **Randomized Token Generation:** Auto-assigns a randomized booking tracking digit to successful transactions.

## 💻 Sample Script Layout (`INPUT.txt`)
```text
ADD_ROOM ClassRoom 101 1 40 Open SmartProjector
ADD_Employee 5001 Nora AlAli HumanResources Coordinator
ADD_EVENT Graduation Ceremony 1718816400000 09.00 13.00 35
BOOKING 101 Graduation 5001
Quit
