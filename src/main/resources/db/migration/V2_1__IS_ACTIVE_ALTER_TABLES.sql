ALTER TABLE public.category ADD COLUMN is_active boolean DEFAULT true;
ALTER TABLE public.subcategory ADD COLUMN is_active boolean DEFAULT true;
ALTER TABLE public.product ADD COLUMN is_active boolean DEFAULT true;

