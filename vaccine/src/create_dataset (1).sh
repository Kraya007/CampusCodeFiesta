#!/bin/sh

tail -n +2 vaccinations_full.csv | grep '2022-' | cut -f 1,3,9 -d ',' | sort -R > vaccinations.csv
