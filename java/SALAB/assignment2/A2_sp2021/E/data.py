#!/usr/local/bin/python3
# -*- coding: utf-8 -*-

from random import randint

if __name__ == '__main__': 
    n = randint(1, 10000)
    s = [randint(1, 1000000) for _ in range(n)]
    print(n)
    print(*s)

