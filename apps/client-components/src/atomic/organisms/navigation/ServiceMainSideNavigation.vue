<template>
  <v-navigation-drawer v-model="navigation">
    <v-list>
      <v-list-item @click="emits('click:button', ViewTypes.Home)">
        ホーム
      </v-list-item>

      <v-list-item @click="emits('click:button', ViewTypes.SearchTask)">
        タスク検索
      </v-list-item>

      <v-list-item @click="emits('click:button', ViewTypes.MyPersonHour)">
        My工数
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script setup lang="ts">
import { ViewTypes } from "@/lib/view/ViewType";

type EmitsEvent = {
  (event: "click:button", value: ViewTypes): void;
};
const emits = defineEmits<EmitsEvent>();

const navigation = defineModel<boolean>("navigation", { default: true });
</script>
