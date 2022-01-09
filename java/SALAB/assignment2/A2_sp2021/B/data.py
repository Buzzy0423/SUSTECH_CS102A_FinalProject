#!/usr/local/bin/python3
# -*- coding: utf-8 -*-

import sys
from itertools import chain
from random import randint

if __name__ == '__main__':
    n = randint(100, 1000)
    a = [randint(100, 100000) for _ in range(n)]
    add = [[randint(100, 100000) for _ in range(randint(0, 10))] for __ in range(n)]
    b = [randint(100, 100000) for _ in range(0, 10)] + list(chain(*[[a[i], *add[i]] for i in range(n)]))
    m = len(b)
    if not randint(0, 1):
        a = a[::-1]
        print('No', file=sys.stderr)
    else:
        print('Yes', file=sys.stderr)
    print(f'{n} {m}')
    print(*a)
    print(*b)
