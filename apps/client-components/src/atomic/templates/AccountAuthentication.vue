<template>
  <v-app>
    <v-main :style="style">
      <slot />
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import { computed } from "vue";

const style = computed(() => {
  return {
    width: "450px",
    marginLeft: "auto",
    marginRight: "auto",
    marginTop: "50px",
  };
});
</script>
