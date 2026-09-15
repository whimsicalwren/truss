package dev.wren.truss.util

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent

fun String.component(): MutableComponent = Component.literal(this)