# Project Report (Short)
## Route Finding Navigation System
**Submitted By: Urvashi Singh**
**Subject: Data Structures / Java Programming**

### 1. Introduction
Navigation apps like Google Maps har din shortest route nikalte hain. Isi idea par based ye ek chhota Java project hai jo do places ke beech sabse chhota rasta (shortest path) nikalta hai. City ko ek Graph ki tarah model kiya gaya hai.

### 2. Objective
- Graph data structure ko real-life example se samajhna
- Dijkstra's Algorithm se shortest path nikalna
- Java Swing se simple front-end (GUI) banana

### 3. Technology Used
- Language: Java
- Front-end: Java Swing (JFrame, JButton, JComboBox, JPanel)
- DSA: Graph, Adjacency List, PriorityQueue, HashMap
- No external library / database - project halka rakha gaya hai

### 4. How It Works
1. 10 places aur unke beech ki roads (distance ke saath) Graph me add hoti hain.
2. User Source aur Destination select karta hai.
3. Dijkstra's Algorithm shortest path calculate karta hai.
4. Result screen par dikhta hai - path, total distance, total stops - aur map par route green colour me highlight hota hai.

### 5. Conclusion
Is project se Graph aur Dijkstra's Algorithm practically samajh aata hai. Future me isme live map, traffic aur naye places add karne ka feature joda ja sakta hai.

---

# Viva Notes - 10 Easy Questions (yaad karne ke liye)

**Q1. Project me kaunsi DSA use hui hai?**
Graph aur Dijkstra's Algorithm. Saath me Adjacency List, HashMap aur PriorityQueue.

**Q2. Graph kya hota hai?**
Nodes aur Edges ka collection. Yahan Place = Node, Road = Edge, Distance = Edge ka Weight.

**Q3. Adjacency List kya hai?**
Har node ke saath uske neighbours ki list store karna. Humne HashMap<String, List> use kiya hai.

**Q4. Dijkstra kya karta hai?**
Ek source se dusre sab places tak ka shortest path nikalta hai. Ye greedy algorithm hai.

**Q5. PriorityQueue kyun use ki?**
Har step par sabse kam distance wala place pehle nikalne ke liye - isse algorithm fast hota hai.

**Q6. Road dono taraf (undirected) kyun hai?**
Kyuki asal me road se dono taraf ja sakte hain, isliye addRoad() dono side edge jodta hai.

**Q7. Agar Source = Destination ho to?**
Program message deta hai "Source and Destination are same" - koi calculation nahi hoti.

**Q8. Time Complexity kya hai?**
Dijkstra with PriorityQueue: O((V + E) log V), jahan V = places, E = roads.

**Q9. Front-end kis se bana hai?**
Java Swing se - JFrame window, dropdown ke liye JComboBox, button aur map ke liye custom JPanel drawing.

**Q10. Future me kya add kar sakte ho?**
Live traffic, Google Maps API, naya place add karna, A* algorithm.

---
*Tip for Urvashi: Code sirf 2 files ka hai. Graph.java pehle samjho (DSA part), phir RouteFinderApp.java (GUI part). Viva me zyada sawal Graph.java se hi aayenge.*
