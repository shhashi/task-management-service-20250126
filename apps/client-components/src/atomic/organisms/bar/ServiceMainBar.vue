<template>
  <v-app-bar color="green">
    <v-app-bar-nav-icon @click="toggle"></v-app-bar-nav-icon>

    <v-toolbar-title>{{ title }}</v-toolbar-title>

    <v-spacer></v-spacer>

    <v-btn icon @click="emits('click:openProjectCreationModalButton')">
      <v-icon>mdi-plus-box</v-icon>
    </v-btn>

    <v-btn icon>
      <v-icon>mdi-account</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script setup lang="ts">
import "@mdi/font/css/materialdesignicons.css";

type Emits = {
  (event: "click:openProjectCreationModalButton"): void;
};
const emits = defineEmits<Emits>();

const open = defineModel<boolean>("open", { default: true });

type Props = {
  title: string;
};
const { title } = defineProps<Props>();

function toggle() {
  open.value = !open.value;
}
</script>
