package com.felippeneves.newmovieapp.core.util

import androidx.paging.PagingConfig

fun getPagingConfig(): PagingConfig =
    PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )