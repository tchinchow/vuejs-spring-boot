<template>
  <div class="hello">
    <p>
    <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service from VueJS</button>
    </p>
    <h3>Response</h3>
    <p>{{ response }}</p>
    <h3>Errors</h3>
    <p>{{ errors }}</p>
    <h3>Misc Values</h3>
    <p>{{ messageTarget }}</p>
    <p>{{ messageBaseUrl }}</p>
  </div>
</template>

<script>
// import {AXIOS} from "@/services/http.service";
import axios from "axios";

export default {
  data() {
    return {
      messageTarget: '',
      messageBaseUrl: '',
      response: [],
      errors: []
    };
  },
  mounted: function () {
    this.messageTarget = process.env.VUE_APP_REMOTE_SERVER
    this.messageBaseUrl = process.env.BASE_URL
  },
  methods: {
    callRestService() {
      console.log('pouet')
      axios
        .get(process.env.VUE_APP_REMOTE_SERVER + `/api/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
    }
  }

}
</script>

<style>

</style>