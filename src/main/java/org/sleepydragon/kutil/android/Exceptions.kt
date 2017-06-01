/*
 * Copyright (C) 2017 Denver Coneybeare <denver@sleepydragon.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleepydragon.kutil.android

/**
 * Exception thrown when a fragment is not found via a search but one was expected to be found.
 */
open class FragmentNotFoundException(message: String) : RuntimeException(message)

/**
 * Exception thrown when a fragment is found via a search but it was not able to be successfully
 * casted to the expected type.
 */
open class FragmentCastException(message: String) : RuntimeException(message)
