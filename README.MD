# N-Way Set-Associative Cache Simulator

A Java-based cache simulator that implements various cache replacement policies (LRU, LFU, FIFO).

## 📋 Table of Contents
- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Demo](#demo)
- [Technical Details](#technical-details)

## 🎯 About

This project simulates an n-way set-assosciative cache. It helps understanding how addresses are stored in the cache under different replacement policies. I created this project to consolidate my learnings of Computer Architecture.  

## ✨ Features

- **Replacement Policies:**
  - LRU (Least Recently Used)
  - LFU (Least Frequently Used)
  - FIFO (First In First Out)

- **Flexible Configuration:**
  - Configurable cache line size
  - Configurable number of sets
  - Configurable associativity (n-way)

- **Visual Output:**
  - Formatted cache state table
  - Hit/Miss Analysis
  - Hexadecimal address parsing

## 🚀 Getting Started

### Prerequisites
- Java JDK 11 or higher

### Compilation

```bash
# Navigate to project directory
cd n-way-set-associative-cache-simulator

# Compile all source files
javac -d bin src/simulator/*.java src/test/*.java
```

## 💻 Usage

### Run the Main Program

```bash
java -cp bin simulator.Main
```

### Run Tests

```bash
java -cp bin test.CacheTest
```

## 🎬 Demo

Run the simulator to see how addresses are cached with different replacement policies:

```bash
# First, compile the project
javac -d bin src/simulator/*.java src/test/*.java

# Then run the demo
java -cp bin simulator.Main
```

### Sample Configuration
```java
Cache cache = new Cache(16, 8, 2, "LRU");
```
- **Cache line size:** 16 bytes
- **Number of sets:** 8
- **Associativity:** 2-way
- **Replacement policy:** LRU

### Address Sequence
```java
String[] addressArr = {
    "0xbfffff00", "0x440", "0xbffffef0", "0xbffffee0",
    "0xbffffed0", "0xbffffec4", "0xbffffec0", "0xbffffec8",
    "0x4c4", "0x440", "0x54f", "0x4d7", "0x440", "0x5c3",
    "0x55555555", "0x5a0", "0xbffffec4", "0xbffffed0",
    "0xbffffee0", "0xbffffef0", "0xbfffff00"
};
```

### Output
```
| Set  | Tag 1      | Tag 2      |
|------|------------|------------|
|  0   | 0x17ffffe  | ---        |
|  1   | ---        | ---        |
|  2   | 0xb        | ---        |
|  3   | ---        | ---        |
|  4   | 0xb        | 0x17ffffd  |
|  5   | 0xaaaaaa   | 0x17ffffd  |
|  6   | 0x17ffffd  | ---        |
|  7   | 0x17ffffd  | ---        |

Hits: 5 | Misses: 16 | Hit Rate: 23.8% | Miss Rate: 76.2%
```

**Interpretation:**
- Each row represents a cache set (0-7)
- Each column shows the tag stored in that position (`---` means empty)
- The statistics show 5 hits out of 21 total accesses (23.8% hit rate)


## 🔧 Technical Details

### Cache Organization
- **Direct-mapped to n-way set-associative** architecture
- Address breakdown: `[Tag | Index | Offset]`
- Configurable parameters for flexible simulation

### Replacement Policies

**LRU (Least Recently Used)**
- Tracks last access time for each cache line
- Evicts the line that hasn't been used for the longest time

**LFU (Least Frequently Used)**
- Tracks access count for each cache line
- Evicts the line with the lowest access count

**FIFO (First In First Out)**
- Maintains insertion order
- Evicts the oldest cache line in the set

### Important Notes
- Only hexadecimal addresses are supported
- Assumes aligned memory accesses (no block spanning)
- Initial cache state is empty
- Uses unsigned long for large address support

Educational use only.
