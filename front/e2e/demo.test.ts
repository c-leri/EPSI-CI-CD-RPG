import { expect, test } from '@playwright/test';

test('home page has expected h1', async ({ page }) => {
	await page.goto('/');
	await expect(page.locator('h1').first()).toBeVisible();
});

test('Click "Charger une sauvegarde" shows modal with correct title ', async ({ page }) => {
	await page.goto('/');

	await page.getByRole('button', { name: /Charger une sauvegarde/i }).click();

	await expect(page.getByText(/Choisissez votre numéro de sauvegarde/i)).toBeVisible();
});

test('Click "annuler" closes modal and removes title ', async ({ page }) => {
	await page.goto('/');

	await page.getByRole('button', { name: /Charger une sauvegarde/i }).click();

	await expect(page.getByText(/Choisissez votre numéro de sauvegarde/i)).toBeVisible();

	// Click the 'annuler' button
	await page.getByRole('button', { name: /Annuler/i }).click();

	// Verify the modal title is gone
	await expect(page.getByText(/Choisissez votre numéro de sauvegarde/i)).toBeHidden();
});
