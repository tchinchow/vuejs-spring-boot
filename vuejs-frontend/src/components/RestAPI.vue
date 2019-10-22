<template>
  <div class="hello">
    <h3>Misc Values</h3>
    <ul>
      <li>Target URL: "{{ messageTarget }}"</li>
      <li>Application Base URL: "{{ messageBaseUrl }}"</li>
    </ul>
    <h3>API</h3>
    <button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service from VueJS</button>
    <h4>Response</h4>
    <ul id="example-1">
      <li v-for="(item, index) in responses" v-bind:item="item" v-bind:index="index" v-bind:key="item.id">
        [{{index}}]: <code>{{item === null ? 'null' : item}}</code>
      </li>
    </ul>
    <h4>Errors</h4>
    <ul id="example-1">
      <li v-for="(item, index) in errors" v-bind:key="index">
        [{{index}}]: <code>{{item === null ? 'null' : item}}</code>
      </li>
    </ul>
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
      responses: [],
      errors: []
    };
  },
  mounted: function () {
    this.messageTarget = process.env.VUE_APP_REMOTE_SERVER
    this.messageBaseUrl = process.env.BASE_URL
  },
  methods: {
    callRestService() {
      axios
        .get(process.env.VUE_APP_REMOTE_SERVER + `/api/hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.responses.push(response.data);
          this.errors.push(null);
        })
        .catch(e => {
          this.responses.push(null);
          this.errors.push(e);
        });
    }
  }

}
</script>

<style>

</style>