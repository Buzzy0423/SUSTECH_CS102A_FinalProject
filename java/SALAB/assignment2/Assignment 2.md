# Assignment 2

出题人：Whexy

验题人：Macromogic



## 1. [Easy] SUSTech Ratio

### Description

For a long time, Whexy doesn't have a girlfriend. So he wants statistics on the ratio of male to female students at SUSTech. For example, he finds out there are 120 boys and 16 girls in the class of 2018 in the department of Computer Science and Engineering.

But 120:16 seems not straight-forward. 15:2 is the most straight-forward representation. Whexy is bad at math, so he counts on you.

Give you two numbers, and you should simplify the ratio to its simplest form.

### Input

Using `System.in`

The input contains one line, with two integer $n$ and $m$ separated by a space.

We ensure that $0 < n, m < 100000000$.

### Output

One line with two interger, denoting the simplest form of the ratio.

### Sample Input

```
120 16
```

### Sample Output

```
15 2
```



## 2. [Medium] Git Merge

### Description

Git is a powerful tool. When you and your partner write conflict codes, git can automatically determine whether you can merge them successfully without any code loss.

To simplify the problem, we assume that the codes are sequences of integers. If one sequence can be transformed into another sequence only by inserting numbers (i.e. you do not delete or swap the numbers), we say that the former sequence can be merged into the latter one.

Now, Whexy and his partner write some conflicting codes due to the lack of communication. Can you please help them to determine whether the code can be merged?

### Input

The input contains three lines.

The first line contains two integers $n$ and $m$, sperated by a space, indicating the length of two sequences.

The second line contains $n$ integers, indicating the first sequence.

The third line contains $m$ integers, indicating the second sequence.

We ensure that $0 < n < m < 5000$, and the numbers in the sequence do not exceed $100000$.

### Output

One line.

If the first sequence can be merged into the second sequence, print `Yes`.

Otherwise, print `No`.

### Sample Input 1

```
5 6
1 3 5 7 9
1 3 3 5 7 9
```

### Sample Output 1

```
Yes
```

### Sample Input 2

```
5 6
1 3 5 7 9
1 2 4 5 7 9
```

### Sample Output 2

```
No
```



## 3. [Medium] CS301

### Description

Embedded System and Microcomputer Principle (CS301) used to be a compulsory course for students majoring in Computer Science in SUSTech. Last year, Whexy enrolled in this course and felt terrible at the final exam.

The final exam contains many binary calculations, and students are not allowed to bring a calculator! In this problem, you can experience the final exam in advance! Give you two binary numbers, add them up and print out the result.

Luckily, you don't have to calculate them with your brain as we were. You have learned Java and use it wisely!

### Input

**Using `System.in`**

The input contains two lines. Each line contains a long binary number.

We ensure that the lengths of the numbers do not exceed 32 in 30% of the cases, 64 in 70% of the cases, and 100000 for 100% of the cases.

### Output

One binary number, indicating the add result.

### Sample Input

```
11011111101010010
10001010111
```

### Sample Output

```
11100001110101001
```



## 4. [Medium] Dating Date

### Description

Whexy and his girlfriend are going to have their first dating. Since it's their first dating, they agreed to set it on a particular date. 

Most dates can be represented by eight digits. For example, 20210313 means March 13th, 2021. An echo date means that the numeric representation of the date is in a palindrome. For example, 20200202 is a valid echo date. 20199102 is not an echo date since the date itself is invalid.

Whexy wants to know how many echo dates there are between the given two dates (including the two dates themselves).

### Input

Using `System.in`

The input contains two lines. Both line contains an 8-digit number.

The first line represents for the beginning date $d_1$.

The second line represents for the ending date $d_2$.

It's ensured that $d_1$ and $d_2$ are real date, and $d_1$ is not later than $d_2$.

### Output

An integer, indicating how many echo dates there are between two given dates.

### Sample Input

```
20200201
20200203
```

### Sample Output

```
1
```



## 5. [Hard] Which Restaurant

### Description

Whexy decides to take his girlfriend to the restaurants in Baoneng City. There're so many restaurants in Baoneng City, and he has no idea about where to go.

Luckily, he got some reviews from XiaoHongShu, a famous mobile APP shows the feedbacks of the customers. In XiaoHongShu, every restaurant has a score, which represents the quality of its service. The score ranges from 0 to 100, with a higher score indicating a better restaurant.

Whexy decides to take his girlfriend to a better restaurant than last time to please his girlfriend. Can you please help him find the **longest** **continuous** segment in the following restaurants' ratings so that the rating **does not drop** in this segment?

### Input

Using `System.in`

The input contains two lines. The first line is an integer $n$, which indicates the number of restaurants.

The next line contains $n$ integers $s_1, s_2, \cdots, s_n$, which are separated by spaces. $s_i$ indicates the score of the $i$-th restaurant.

We ensure that $0 < n \leq 10000$, and $0 < s_i \leq 1000000$ for all $1 \leq i \leq n$.

### Output

One line contains serveral integers $s_i, s_{i+1}, \cdots, s_j$, separated by spaces. These integers are the longest continuous segment. If there are more than one longest coninuous segment, print the last one.

### Sample Input 1

```
6
1 1 4 5 1 4
```

### Sample Output 1

```
1 1 4 5
```

### Sample Input 2

```
12
6 7 8 9 10 1 1 5 7 9 1 11
```

### Sample Output 2

```
1 1 5 7 9
```

Explanation: There are two longest continuous segment `6 7 8 9 10` and `1 1 5 7 9`, you should print the last one.







