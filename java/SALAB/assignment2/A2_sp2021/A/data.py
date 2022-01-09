#!/usr/local/bin/python3
# -*- coding: utf-8 -*-

import sys
from random import randint

if __name__ == '__main__':
    d, ni, mi = randint(100, 10000), randint(1, 10000), randint(1, 10000)
    n, m = d * ni, d * mi
    print(f'{n} {m}')
    i = 2
    while i <= ni and i <= mi:
        while ni % i == 0 and mi % i == 0:
            ni /= i;
            mi /= i;
        i += 1
    print(f'{ni} {mi}', file=sys.stderr)

