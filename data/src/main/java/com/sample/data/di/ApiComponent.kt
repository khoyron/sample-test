package com.sample.data.di

import com.sample.data.endpoint.GetDataNetwork
import dagger.Component

@CustomScope
@Component(modules = [ApiModule::class], dependencies = [NetworkComponent::class])
interface ApiComponent {
    fun inject(getDataLogin: GetDataNetwork)
}
