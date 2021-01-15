<template>
  <v-row no-gutters>
    <v-col cols="12" xs="12" sm="5">
      <v-card class="pa-2" outlined >
        .col-12 .col-sm-6 .col-md-8
      </v-card>
    </v-col>
    <v-col cols="12" xs="12" sm="7">
      <v-card class="pa-2" outlined >
        .col-6 .col-md-4
      </v-card>
    </v-col>
  </v-row>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component
export default class TodayTask extends Vue { }
</script>