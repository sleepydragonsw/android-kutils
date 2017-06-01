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
import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FragmentManagerExtTest {

    @Test
    fun test_findFragmentByIdOrThrow() {
        class FoodFragment : Fragment()
        open class AnimalFragment : Fragment()
        class DogFragment : AnimalFragment()

        val id = 5

        run {
            val fm = Mockito.mock(FragmentManager::class.java)
            val fragment = DogFragment()
            Mockito.`when`(fm.findFragmentById(id)).thenReturn(fragment)
            assertSame(fragment, fm.findFragmentByIdOrThrow<DogFragment>(id))
        }
        run {
            val fm = Mockito.mock(FragmentManager::class.java)
            val fragment = DogFragment()
            Mockito.`when`(fm.findFragmentById(id)).thenReturn(fragment)
            assertSame(fragment, fm.findFragmentByIdOrThrow<AnimalFragment>(id))
        }
        run {
            val fm = Mockito.mock(FragmentManager::class.java)
            Mockito.`when`(fm.findFragmentById(id)).thenReturn(null)
            val exception = assertFailsWith<FragmentNotFoundException> { fm.findFragmentByIdOrThrow<Fragment>(id) }
            assertEquals("fragment with ID $id not found", exception.message)
        }
        run {
            val fm = Mockito.mock(FragmentManager::class.java)
            val fragment = FoodFragment()
            Mockito.`when`(fm.findFragmentById(id)).thenReturn(fragment)
            val exception = assertFailsWith<FragmentCastException> { fm.findFragmentByIdOrThrow<AnimalFragment>(id) }
            assertEquals("fragment with ID $id was found, but was an instance of ${fragment::class} "
                    + "when expecting an instance of ${AnimalFragment::class}", exception.message)
        }
    }

}
