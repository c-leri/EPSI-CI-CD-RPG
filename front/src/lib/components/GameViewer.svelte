<script lang="ts">
  import { LucideRefreshCw } from 'lucide-svelte';
  import { roomApi } from '../Api/apicallexample';
  import type { Game } from '$lib/types/api';
  import { onMount } from 'svelte';

  let loading = false;
  let error: string | null = null;
  let game: Game | null = null;

  async function fetchGame() {
    loading = true;
    error = null;

    try {
      // Vérifier d'abord dans le localStorage
      const storedGame = localStorage.getItem('game');
      if (storedGame) {
        game = JSON.parse(storedGame);
      } else {
        // Si pas dans le localStorage, faire une get
        const response = await roomApi.getGame();
        game = response.data;
        // Sauvegarder dans le localStorage
        localStorage.setItem('game', JSON.stringify(game));
      }
    } catch (e) {
      error = e instanceof Error ? e.message : 'Une erreur est survenue';
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    fetchGame();
  });
</script>

<div class="p-4 max-w-2xl mx-auto">
  <div class="flex justify-between items-center mb-6">
    <h1 class="text-2xl font-bold">Détails du Jeu</h1>
    <button
            on:click={fetchGame}
            class="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50"
            disabled={loading}
    >
      <LucideRefreshCw class={loading ? 'animate-spin' : ''} />
      Rafraîchir
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
  {:else if game}
    <div class="bg-white shadow-lg rounded-lg p-6">
      <div class="border rounded-lg p-4 bg-gray-50">
        <h3 class="font-medium">Jeu #{game.id}</h3>
        <!-- ADI Ajoute ici d'autres propriétés qulle tu veux   -->
      </div>
    </div>
  {/if}
</div>