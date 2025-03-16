<template>
  <v-data-table :headers="headers" :items="tasks"></v-data-table>
</template>

<script setup lang="ts">
import { fetchTasks } from "@/lib/api/tasks";
import { computedAsync } from "@vueuse/core";
import { ref } from "vue";

type Props = {
  projectId: string;
  statusId?: number;
  assign?: number;
};
const { projectId, statusId, assign } = defineProps<Props>();

const tasks = computedAsync(() => {
  return fetchTasks({ projectId: projectId });
});

const headers = ref([
  { title: "タスクID", key: "taslId" },
  { title: "タスク名", key: "taskName" },
  { title: "ステータス", key: "statusId" },
  { title: "終了日", key: "statusName" },
  { title: "担当者", key: "projectId" },
]);
</script>
