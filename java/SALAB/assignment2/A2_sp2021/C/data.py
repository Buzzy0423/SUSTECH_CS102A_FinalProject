#!/usr/local/bin/python3
# -*- coding: utf-8 -*-

from random import randint

if __name__ == '__main__':
    a, b = randint(1 << 64, 1 << 100000), randint(1 << 64, 1 << 100000)
    print(bin(a)[2:])
    print(bin(b)[2:])

