<script lang="ts">
  import { onMount } from 'svelte';
  import { LucideRefreshCw } from 'lucide-svelte';

  type Room = {
    id: number;
    type: string;
  };

  type Scenario = {
    id: string;
    rooms: Room[];
  };

  type ApiResponse<T> = {
    status: string;
    message: string;
    data: T;
  };

  let scenario: Scenario | null = null;
  let loading = false;
  let error: string | null = null;

  async function generateNewScenario() {
    try {
      loading = true;
      error = null;
      const response = await fetch('http://localhost:8080/api/scenario/new');
      const data: ApiResponse<Scenario> = await response.json();
      
      if (data.status === 'success') {
        scenario = data.data;
      } else {
        error = data.message;
      }
    } catch (e) {
      return e;
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    generateNewScenario();
  });
</script>

<div class="p-4 max-w-2xl mx-auto">
  <div class="flex justify-between items-center mb-6">
    <h1 class="text-2xl font-bold">Générateur de Scénario RPG</h1>
    <button
      on:click={generateNewScenario}
      class="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50"
      disabled={loading}
    >
      <LucideRefreshCw class={loading ? 'animate-spin' : ''} />
      Générer nouveau
    </button>
  </div>

  {#if error}
    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
      <span class="block sm:inline">{error}</span>
    </div>
  {/if}

  {#if loading}
    <div class="flex justify-center items-center p-8">
      <div class="animate-pulse text-gray-600">Chargement...</div>
    </div>
  {:else if scenario}
    <div class="bg-white shadow-lg rounded-lg p-6">
      <h2 class="text-xl font-semibold mb-4">Scénario #{scenario.id}</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        {#each scenario.rooms as room (room.id)}
          <div class="border rounded-lg p-4 bg-gray-50">
            <h3 class="font-medium">Salle {room.id}</h3>
            <p class="text-gray-600">Type: {room.type}</p>
          </div>
        {/each}
      </div>
    </div>
  {/if}
</div>