
# Charity Boxes Management

Application for managing collection boxes during fundrising events for charity organizations
## Tech Stack
- Spring Boot
- Spring Web
- Spring Data JPA

Tested on Eclipse Temurin 17 JDK and REST Client VS Code Extension

## How to build and run the applicaton

#### 1. Pull this repository from GitHub

#### 2. Compile and run the program in your favourite IDE (IntelliJ recommenedd)

#### 3. Open requests files to send REST request - REST Client extension for VS Code is recommended

- **requests_fundraising_events.http** - create fundraising events, show financial report
- **requests_boxes.http** - create/assign/empty/remove boxes, add money, show list of all boxes

**Full test sample queries are located in file:**
- **requests_test.http** - scenario of requests placed in order to test all functionalities of the application
## 
<img src="https://i.imgur.com/Cqe65V3.png" width="25%" height="auto"><br>
<img src="https://i.imgur.com/87oPwUN.png" width="80%" height="auto">

## If you encounter java: cannot find symbol Lombok IntelliJ error on compilation

Go to Settings -> Compiler -> Annotation Processors

<img src="https://i.imgur.com/zpKDe2v.png" width="80%" height="auto">

## REST API endpoints

- Create a new fundraising event.
- Register a new collection box.
- List all collection boxes. Include information if the box is assigned (but don’t expose to what
fundraising event) and if it is empty or not (but don’t expose the actual value in the box).
- Unregister (remove) a collection box (e.g. in case it was damaged or stolen).
- Assign the collection box to an existing fundraising event.
- Put (add) some money inside the collection box.
- Empty the collection box i.e. “transfer” money from the box to the fundraising event’s account.
- Display a financial report with all fundraising events and the sum of their accounts.