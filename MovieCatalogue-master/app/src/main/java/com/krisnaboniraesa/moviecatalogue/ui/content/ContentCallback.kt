package com.krisnaboniraesa.moviecatalogue.ui.content

import com.krisnaboniraesa.moviecatalogue.data.source.local.entity.DataEntity

interface ContentCallback {
    fun onItemClicked(data: DataEntity)
}