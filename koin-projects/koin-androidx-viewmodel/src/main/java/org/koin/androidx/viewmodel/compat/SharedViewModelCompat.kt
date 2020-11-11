/*
 * Copyright 2017-2021 the original author or authors.
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
package org.koin.androidx.viewmodel.compat

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ViewModelOwner.Companion.from
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

/**
 * Functions to help shared view models in Java
 *
 * @author Jeziel Lago
 */
object SharedViewModelCompat {

    /**
     * Lazy getByClass a viewModel instance shared with Activity
     *
     * @param fragment - Fragment
     * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
     * @param from - ViewModelStoreOwner that will store the viewModel instance. Examples: "parentFragment", "activity". Default: "activity"
     * @param parameters - parameters to pass to the BeanDefinition
     * @param clazz
     */
    @JvmOverloads
    @JvmStatic
    fun <T : ViewModel> sharedViewModel(
        fragment: Fragment,
        clazz: Class<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null
    ): Lazy<T> = fragment.sharedViewModel(qualifier, null, { from(fragment.requireActivity().viewModelStore) },
        clazz.kotlin,
        parameters)

    /**
     * Get a shared viewModel instance from underlying Activity
     *
     * @param fragment - Fragment
     * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
     * @param from - ViewModelStoreOwner that will store the viewModel instance. Examples: ("parentFragment", "activity"). Default: "activity"
     * @param parameters - parameters to pass to the BeanDefinition
     * @param clazz
     */
    @JvmOverloads
    @JvmStatic
    fun <T : ViewModel> getSharedViewModel(
        fragment: Fragment,
        clazz: Class<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null
    ): T {
        return fragment.getSharedViewModel(qualifier, null, { from(fragment.requireActivity().viewModelStore) },
            clazz.kotlin,
            parameters)
    }
}