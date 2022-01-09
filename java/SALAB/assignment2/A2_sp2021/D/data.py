#!/usr/local/bin/python3
# -*- coding: utf-8 -*-

from random import randint
from datetime import date, timedelta

if __name__ == '__main__':
    oneday = timedelta(days=1)
    a, b = randint(0, 3652058), randint(0, 3652058)
    if a > b:
        a, b = b, a
    d1 = date.min + a * oneday
    d2 = date.min + b * oneday
    print(f'{d1.year:04d}{d1.month:02d}{d1.day:02d}')
    print(f'{d2.year:04d}{d2.month:02d}{d2.day:02d}')
