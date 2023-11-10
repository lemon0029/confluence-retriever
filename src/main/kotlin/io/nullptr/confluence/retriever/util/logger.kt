package io.nullptr.confluence.retriever.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T : Any> T.thisLogger(): Logger = LoggerFactory.getLogger(T::class.java)