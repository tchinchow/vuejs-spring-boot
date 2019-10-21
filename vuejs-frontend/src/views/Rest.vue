<template>
  <div class="rest">
    <RestAPI/>
  </div>
</template>

<script>
// @ is an alias to /src
import RestAPI from '@/components/RestAPI.vue'

export default {
  name: 'home',
  components: {
    RestAPI
  }
}
</script>
