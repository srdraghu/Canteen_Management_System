# Canteen Management App — Project Summary & Workflow

## Project idea (one-liner)
A small-scale canteen management system implemented using pure OOP, custom exception handling, and multithreading to practice and master the MVC pattern.

## Goals
- Learn and demonstrate clean OOP design (interfaces, inheritance, encapsulation)
- Implement custom exceptions for business rules
- Handle concurrent orders safely using multithreading (BlockingQueue, Semaphore, locks)
- Enforce MVC separation (models, controllers, views)
- Practice Git workflow and unit/concurrency testing

## Constraints (non‑functional)
- Keep scope minimal — only Admin and User roles
- Menu split into three categories: Breakfast, Afternoon, Dinner
- No external DB: in-memory models (use collections)
- No frameworks; plain Java SE (Console-based view)
- Minimal third-party libs (only for tests if needed)

## High-level package map
- `com.canteen.model` — domain models: `Canteen`, `Menu`, `FoodItem`, `Category`, `Order`, `User`, `Admin`, `Feedback`
- `com.canteen.controller` — business logic: `OrderController`, `AdminController`, `CanteenController`
- `com.canteen.view` — I/O layer: `ConsoleView` (or simple UI later)
- `com.canteen.exception` — custom exceptions
- `com.canteen.concurrent` — `OrderProcessor`, `Dispatcher`, utilities for concurrency
- `com.canteen.util` — helpers (ID generation, timers, simple logger)

## Core class hierarchy (summary)
- `Canteen` (aggregates `Menu`, `Admin`, `List<User>`, order queue)
- `Menu` (maps categories to `FoodItem` instances)
- `FoodItem` (abstract) → `Idli`, `Dosa`, etc. (variants as subclasses)
- `User` (can `placeOrder`, `giveFeedback`)
- `Admin` (can `addStock`, `viewCollections`, `viewFeedback`)
- `Order` (id, userId, items map, status)

## Exceptions (business rules)
- `ItemNotAvailableException` — requested item not on menu
- `OrderedMaximumException` — user exceeded per-item limit
- `InsufficientStockException` — not enough stock at processing time
- Use checked exceptions in controller boundaries; catch & convert into user messages in view

## Concurrency model & decisions
- Orders are submitted to a `BlockingQueue<Order>` by controllers
- An `OrderProcessor` (runnable) consumes the queue and processes orders
- Use a `Semaphore` to limit the number of concurrently processed orders
- Use `AtomicInteger` for simple per-item stock; for multi-item order atomicity, use coarse `synchronized(menu)` or ordered per-item locks
- Design for small scale: correctness over performance

## Development workflow (milestones)
1. **Project setup & repo**
   - Initialize modules, packages, `README.md`, `.gitignore`
   - Create `main` module and basic `App` entry that shows a welcome message
2. **Model layer (Day 1–2)**
   - Implement `FoodItem`, a few concrete items, `Menu`, `Canteen`, `Order`, `User`, `Admin`
   - Add basic unit tests for getters and stock operations
3. **Controller layer (Day 3–4)**
   - Implement `OrderController` and `AdminController` with validation and exceptions
   - Implement simple `Menu` APIs: `hasItem`, `getItemById`, `addStock`
4. **View & interactive console (Day 4–5)**
   - `ConsoleView` to display menu, take order input, show results/errors
   - Wire `OrderController` to view: `submitOrder` -> confirm or show exception message
5. **Concurrency & processors (Day 6–7)**
   - Add `BlockingQueue<Order>`, `OrderProcessor` threads, `Semaphore` control
   - Create simulated users threads to stress test stock handling
6. **Edge cases & exception flows (Day 8)**
   - Test multi-item atomicity, rollback behavior, and exception messages
7. **Testing & validation (Day 9)**
   - Unit tests for controllers; concurrency tests for race conditions
   - Manual acceptance: simulate 50 rapid orders for an item
8. **Polish & README (Day 10)**
   - Finalize README with instructions to run, branch strategy, and known limitations

   
