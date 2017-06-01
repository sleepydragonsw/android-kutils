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

import android.app.Fragment
import android.app.FragmentManager

/**
 * Find a fragment by its ID and throw an exception if it is not found or was found but is not an
 * instance of the expected type.
 * @param id the ID of the fragment to find.
 * @return the fragment that was found.
 * @throws FragmentNotFoundException if no fragment was found with the given ID.
 * @throws FragmentCastException if a fragment was found with the given ID but was not an instance
 * of the expected type.
 * @see FragmentManager.findFragmentById
 */
inline fun <reified T : Fragment> FragmentManager.findFragmentByIdOrThrow(id: Int): T {
    val fragment = findFragmentById(id)
    if (fragment === null) {
        throw FragmentNotFoundException("fragment with ID $id not found")
    } else if (fragment is T) {
        return fragment
    } else {
        throw FragmentCastException("fragment with ID $id was found, but was an instance "
                + "of ${fragment::class} when expecting an instance of ${T::class}")
    }
}

/**
 * Find a fragment by its tag and throw an exception if it is not found or was found but is not an
 * instance of the expected type.
 * @param tag the tag of the fragment to find.
 * @return the fragment that was found.
 * @throws FragmentNotFoundException if no fragment was found with the given tag.
 * @throws FragmentCastException if a fragment was found with the given tag but was not an instance
 * of the expected type.
 * @see FragmentManager.findFragmentByTag
 */
inline fun <reified T : Fragment> FragmentManager.findFragmentByTagOrThrow(tag: String): T {
    val fragment = findFragmentByTag(tag)
    if (fragment === null) {
        throw FragmentNotFoundException("fragment with tag $tag not found")
    } else if (fragment is T) {
        return fragment
    } else {
        throw FragmentCastException("fragment with tag $tag was found, but was an instance "
                + "of ${fragment::class} when expecting an instance of ${T::class}")
    }
}
