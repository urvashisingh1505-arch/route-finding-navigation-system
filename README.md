# Route Finding Navigation System
**Made by: Urvashi Singh**
**Language: Java | Concept: DSA (Graph + Dijkstra's Algorithm) | Front-end: Java Swing**

A simple navigation system that finds the shortest route between two places in a city.

---

## What does this project do?
- Shows a small city map (10 places of Lucknow)
- User selects a **Source** and a **Destination**
- Clicks **Find Shortest Route**
- The system calculates the shortest path, shows total distance in km, and highlights the route in green on the map

Example:
```
Shortest Route:
Charbagh -> Hazratganj -> Gomti Nagar

Total Distance: 10 km
Total Stops: 3
```

---

## DSA Used (Easy to Explain)
| Concept | Used For |
|---|---|
| **Graph** | City ko graph banaya - Place = Node, Road = Edge |
| **Adjacency List** | Har place se judi roads store karne ke liye (HashMap + ArrayList) |
| **Dijkstra's Algorithm** | Sabse chhota (shortest) route nikalne ke liye |
| **PriorityQueue (Min-Heap)** | Har step par sabse kam distance wala place pehle chunne ke liye |
| **HashMap** | Distance aur parent (previous place) store karne ke liye |

**Dijkstra in 4 lines:**
1. Source ki distance = 0, baki sab = Infinity
2. Sabse kam distance wala unvisited place chuno
3. Uske neighbours check karo - agar chhota rasta mile to update karo
4. Destination milne tak repeat karo, phir parent se path wapas banao

---

## Files
```
src/
  Graph.java           -> Graph + Dijkstra's Algorithm (main DSA logic)
  RouteFinderApp.java  -> Swing GUI + Map drawing + Main method
run.bat                -> Windows par 1-click run
run.sh                 -> Linux/Mac par run
```

Only 2 Java files - intentionally kept light and easy to understand.

---

## How to Run

### Windows (easiest)
Double-click `run.bat`

### Command Line (any OS)
```bash
javac -d out src/*.java
java -cp out RouteFinderApp
```
Java 8 or above required. No external library needed.

---

## Features
- 10 places, 14 roads with real approx distances
- Shortest path + total distance + total stops
- Map par route green highlight hota hai
- Source = Destination ho to proper message aata hai
- Simple, clean GUI - no heavy design

## Future Scope (if teacher asks)
- Live traffic / Google Maps API jodna
- Naya place / road add karne ka option
- A* Algorithm se aur fast search
- Multiple routes dikhana

---
*This is a mini-project for learning Graph and Dijkstra's Algorithm in Java.*
