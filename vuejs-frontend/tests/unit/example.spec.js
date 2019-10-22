import { shallowMount, createLocalVue } from '@vue/test-utils'
import HelloWorld from '@/components/HelloWorld.vue'

import Vuetify from 'vuetify'
const localVue = createLocalVue()
localVue.use(Vuetify)

describe('HelloWorld.vue', () => {
  it('renders props.msg when passed', () => {
    const msg = 'Welcome to Vuetify'
    // const wrapper = shallowMount(HelloWorld, {
    //   propsData: { msg }
    // })
    const wrapper = shallowMount(HelloWorld, { localVue } )
    expect(wrapper.text()).toMatch(msg)
  })
})
